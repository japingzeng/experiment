package experiment.javaIO;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @ClassName: JavaIOTest 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author japing 
 * @date 2016年11月25日 下午7:45:06 
 *
 */
public class JavaIOTest {
	
	public static void main(String[] args) {
		AtomicInteger aInteger = new AtomicInteger();
		isExistFile(new File("D:/FF/FFF"));
		isExistFile(new File("D:\\FF\\FFF"));
//		isExistFile(new File("D:\\"));
//		System.out.println("===========================================================================================");
//		isExistFile(new File("D:"));
	}
	
	public static void isExistFile(File f) {
		if (!f.exists()) {
			System.out.println("have no file ");
		}
		
		File[] name = f.listFiles();
		System.out.println(f.getAbsolutePath() + ":" + name.length);
		for (File file : name) {
			if (file.isDirectory()) {
				isExistFile(file);
			} else {
				System.out.println(file.getName() + "=" + file.getAbsolutePath());
			}
		}
	}
}
