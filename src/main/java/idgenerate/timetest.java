package idgenerate;

import idgenerate.util.DateUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Value;


public class timetest {
	private static final String datestr = "2016-12-07 00:00:00";
	private static final String format = "yyyy-MM-dd HH:mm:ss";
	public static void main(String[] args) {
		long systime = System.currentTimeMillis();
		System.out.println(systime);
		System.out.println(Long.toHexString(systime));
	    System.out.println(Long.toBinaryString(systime));
	    ConcurrentHashMap<Long, Long> hasmap = new ConcurrentHashMap<>();
	    long tmp = systime << 23;
	    long test = systime ^ 0xffffffff;
	    long test1 = test;
	   
	    System.out.println("test1=" + Long.toBinaryString(test1));
	    
	    System.out.println("tmp  =" + Long.toBinaryString(tmp));
	    System.out.println(Long.toBinaryString(test));
	    
	    long machineHouse = 1;// << 38;
	    System.out.println("machineHouse=" + Long.MAX_VALUE);
	    long result = systime ^ machineHouse;
	    System.out.println("result=" + Long.toBinaryString(result));
	    Date fixedDate = DateUtils.getFormatDate(datestr, format);
	    long fixed = fixedDate.getTime();
	    System.out.println(Long.toBinaryString(fixed));
	    Integer a = 0x1 ^ 0x0111;
	    System.out.println(Integer.toBinaryString(a));
	    
	    
	    
	    
	    try {
			InetAddress address = InetAddress.getLocalHost();
			long b = System.currentTimeMillis();
			int ip = address.getHostAddress().split(".").hashCode();
			
			long e = System.currentTimeMillis();
			System.out.println("b2=" +Long.toBinaryString(b));
			System.out.println("e2=" + Long.toBinaryString(e));
			System.out.println("b=" + b);
			System.out.println("c=" + e);
			System.out.println(e-b);
			
			
			
			long sequenceMask = -1L << 7L;
			System.out.println(Long.toBinaryString(-1L));
			System.out.println(Long.toBinaryString(sequenceMask));
			System.out.println(Long.toBinaryString(-1L ^ sequenceMask));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	}
}
