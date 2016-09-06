package experiment.vmTest;
/**
 * 
 * @ClassName: DeadLockTest 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author japing 
 * @date 2016年9月6日 下午4:02:07 
 *
 */
public class DeadLockTest {
	static class SynAddRunnble implements Runnable {
		private int a;
		private int b;
		
		public SynAddRunnble(int a, int b) {
			this.a = a;
			this.b = b;
		}
		
		
		public void run() {
			// TODO Auto-generated method stub
			synchronized (Integer.valueOf(a)) {
				synchronized (Integer.valueOf(b)) {
					System.out.println(a+b);
				}
			}
		}
		
		public static void main(String[] args) {
			for (int i = 0; i < 100; i++) {
				new Thread(new SynAddRunnble(1, 2)).start();
				new Thread(new SynAddRunnble(2, 1)).start();
			}
		}
		
	}
}
