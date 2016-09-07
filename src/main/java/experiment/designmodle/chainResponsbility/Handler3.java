package experiment.designmodle.chainResponsbility;

public class Handler3 extends AbstractHandler {
	private Level level;
	public Handler3(Level level) {
		this.level = level;
	}
	
	@Override
	public Response response(Request request) {
		System.out.println("[***" + getClass().getName() + ": 处理请求   ***]");
		return null;
	}
	
	@Override
	protected Level getHandlerLevel() {
		return level;
	}
	
	
	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	
}
