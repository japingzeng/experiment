package experiment.designmodle.builder2;

public class WheelBuilder extends Builder<Wheel> {
	private Wheel wheel = new Wheel();
	@Override
	public Wheel make() {
		wheel.setName("laferi-wheel");
		wheel.setPrice("100W");
		return wheel;
	}

}
