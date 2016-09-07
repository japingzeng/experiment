package experiment.designmodle.chainResponsbility;
/**
 * 
 * @ClassName: AbstractHandler 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author japing 
 * @date 2016年9月7日 下午6:21:33 
 *
 */
public abstract class AbstractHandler {
	private AbstractHandler nextHandler;
	
	
	
	public final Response handleRequest(Request request) {
		Response response = null;
		try {
			if (this.getHandlerLevel().above(request.getLevel())) {
				response = this.response(request);
			} else {
				if (this.nextHandler != null) {
					this.nextHandler.handleRequest(request);
				} else {
					System.out.println("[***没有合适的处理器***]");
				}
			}
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		return response;
	}
	
	protected abstract Level getHandlerLevel();
	public abstract Response response(Request request);

	
	public void setNextHandler(AbstractHandler nextHandler) {
		this.nextHandler = nextHandler;
	}
}
