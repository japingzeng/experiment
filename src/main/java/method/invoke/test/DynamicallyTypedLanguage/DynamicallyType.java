package method.invoke.test.DynamicallyTypedLanguage;

import java.io.PrintStream;
/**
 * 
 * @ClassName: DynamicallyType 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author japing 
 * @date 2016年12月1日 上午11:49:02 
 *
 */
public class DynamicallyType {
	
	public static void main(String[] args) {
		PrintStream obj = System.out;
		
		obj.println("hello,world");
	}
	
//	function A(str) { this.str = str;this.println = function() { alert (this.str) };}
//	function B(str) { this.str = str;this.println = function() { alert (this.str) };}
//	var obj = new A("hello,world");
//  obj.println()
}
