package experiment.designmodle.chainexecute;
/**
 * 
 * @ClassName: Interceptor 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author japing 
 * @date 2016年11月6日 下午7:24:10 
 *
 */
public interface Interceptor {
	
	public Object intercept(InterptorChain interptorChain);
}
