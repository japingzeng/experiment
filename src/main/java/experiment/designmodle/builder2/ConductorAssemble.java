package experiment.designmodle.builder2;

public class ConductorAssemble {
	private static WheelBuilder wheelBuilder = new WheelBuilder();
	private static FrameBuilder frameBuilder = new FrameBuilder();
	
	
	
	public static void main(String[] args) {
		Wheel wheel = (Wheel) wheelBuilder.make();
		Frame frame = (Frame) frameBuilder.make();
		wheel.showName();
		wheel.showPrice();
		frame.showName();
		frame.showPrice();
	}
	
}
