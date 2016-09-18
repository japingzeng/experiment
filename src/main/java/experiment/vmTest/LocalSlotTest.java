package experiment.vmTest;
/**
 * 
 * @ClassName: LocalSlotTest 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author japing 
 * @date 2016年9月12日 上午10:18:46 
 *
 */
public class LocalSlotTest {
	//test1 64MB不会被回收
//	public static void main(String[] args) {
//		byte[] placeholder = new byte[64*1024*1024];
//		System.gc();
//	}
	
	//test2 64MB作用域被限制在花括作用域内  依然不会被回收，根本原因是虽然代码已经离开了placeholder的作用域，但在此之后没有任何变量对局部变量表的读写操作，placeholder
	//原本所占用的Slot还没有被其他变量所复用，所以作为GC Roots一部分的局部变量仍然保持对他的关联
//	public static void main(String[] args) {
//		{
//			byte[] placeholder = new byte[64*1024*1024];	
//		}
//		
//		System.gc();
//	}
	
	//test3 gc前添加 int a = 0 这一奇怪的招式
	public static void main(String[] args) {
		{
			byte[] placeholder = new byte[64*1024*1024];	
		}
		
		int a = 0;
		System.gc();
	}
	
	
}
