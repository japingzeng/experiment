package experiment.vmTest.dynamicProxy;
import java.io.FileOutputStream;
import java.io.IOException;

import sun.misc.ProxyGenerator;
/**
 * 
 * @ClassName: ProxyUtils 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author japing 
 * @date 2016年9月18日 下午4:01:54 
 *
 */
@SuppressWarnings("restriction")
public class ProxyUtils {
	/**
	 * 
	 * @param clazz 需要生成动态代理类的类
	 * @param ProxyName 动态生成的代理类的名称
	 * @Description: TODO(这里用一句话描述这个类的作用) 
	 * @author japing 
	 * @date 2016年9月18日 下午4:02:56
	 */
	public static void generateClassFile(String path, Class<?> [] interfaces, String ProxyName) {
		//根据类型信息和提供的代理类名称生成字节码

		byte[] sourcefile = ProxyGenerator.generateProxyClass(ProxyName, interfaces);
		FileOutputStream  out = null;
		try {
		//	String filePath = "F:/temp" + "/" + ProxyName + ".class";
			String filePath = path + ProxyName + ".class";
			out = new FileOutputStream(filePath);
			out.write(sourcefile);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != out) 
					out.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	}
}
