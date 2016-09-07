package experiment.designmodle.chainResponsbility;

public class Handler1 extends AbstractHandler {
	private Level level;
	public Handler1(Level level) {
		this.level = level;
	}
	
	@Override
	public Response response(Request request) {
		System.out.println("[***" + getClass().getName() + ": 处理请求   ***]");
		return null;
	}
	
	@Override
	protected Level getHandlerLevel() {
		// TODO Auto-generated method stub
		return level;
	}
	
	
	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	
}
