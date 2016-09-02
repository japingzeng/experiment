package experiment.designmodle.buildermodle;

public class builderTest {
	public static void main(String[] args) {
		Zoo zoo = new Zoo.Builder("老虎", "熊猫")
		 .cat("猫")
		 .dog("狗")
		 .owl("猫头鹰")
		 .build();
		System.out.println(zoo.getCat() + zoo.getOwl());
	}
	
	
}
