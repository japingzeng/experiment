package experiment.other;

import java.math.BigDecimal;

public class testBigdecimal {
	public static void main(String[] args) {
		BigDecimal b1 = new BigDecimal(1.0);
		BigDecimal b2 = new BigDecimal(0);
		BigDecimal b3 = new BigDecimal(0.0);
		System.out.println(b1.compareTo(b2) == 0 ? "true" : "false");
		System.out.println(b3.compareTo(b2) == 0 ? "true" : "false");

		Integer a = new Integer(1);
		int aa = 1;
		Integer aaa = new Integer(1);
		Integer bb = new Integer(3344);
		Integer bbb = new Integer(3344);
		System.out.println(a == aa ? "相等": "不相等");

		System.out.println(a == aaa ? "相等": "不相等");

		System.out.println(bb == bbb ? "相等": "不相等");
	}
}
