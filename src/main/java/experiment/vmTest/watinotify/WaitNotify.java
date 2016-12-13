package experiment.vmTest.watinotify;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 
 * @ClassName: WaitNotify 
 * @Description: TODO(通过该测试，用以说明当一个线程从wait方法返回时，是从调用wait方法处返回，返回时是一定持有了锁，继续往下执行，不会再走一遍进入同步块的过程，
 * 意味着是是恢复了栈的现场，然后继续执行的，这个依托于同步机制) 
 * @author japing 
 * @date 2016年12月13日 上午11:13:16 
 *
 */
public class WaitNotify {
	
	private static volatile boolean flag = true;
	private static Object lock = new Object();
	
	public static void main(String[] args) {
		Thread waitThread = new Thread(new WaitThread(), "waitThread");
		Thread notiyThread = new Thread(new NotifyThread(), "notifyThread");
		waitThread.start();
		notiyThread.start();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("test finish");
		
	}
	private static class WaitThread implements Runnable {

		@Override
		public void run() {
			
			synchronized (lock) {
				System.out.println("enter wait thread");
				while (flag) {
					try {
						System.out.println("[" + Thread.currentThread().getName() + "]" + " flag is " + flag + "@time" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("[" + Thread.currentThread().getName() + "]" + " flag is" + flag + "do somethine" + new SimpleDateFormat("HH:mm:ss").format(new Date()) );
			}
			
		}
	}
	
	private static class NotifyThread implements Runnable {

		@Override
		public void run() {
			synchronized (lock) {	
				try {
					System.out.println("[" + Thread.currentThread().getName() + "]" + " firtst entry " + "@time" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
					Thread.sleep(2000);
					System.out.println("休眠2秒结束");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			synchronized (lock) {
				System.out.println("[" + Thread.currentThread().getName() + "]" + " second entry " + "@time" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
				lock.notify();
			}
		}
		
	}
}
