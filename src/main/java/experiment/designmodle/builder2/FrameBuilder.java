package experiment.designmodle.builder2;

public class FrameBuilder extends Builder<Frame> {
	
	private Frame frame = new Frame();
	@Override
	public Frame make() {
		frame.setName("laferi-frame");
		frame.setPrice("500W");
		return frame;
	}

}
