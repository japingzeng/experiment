package experiment.designmodle.prototypeM;
/**
 * 
 * @ClassName: Prototype 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author japing 
 * @date 2016年9月5日 下午6:08:00 
 *
 */
public class Prototype implements Cloneable {
	public Prototype clone() {
		Prototype prototype = null;
		try {
			prototype = (Prototype) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return prototype;
		
	}
}
