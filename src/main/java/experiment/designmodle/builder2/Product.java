package experiment.designmodle.builder2;
/**
 * 
 * @ClassName: Product 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author japing 
 * @date 2016年9月3日 下午2:44:32 
 *
 */
abstract class Product {
	private String name;
	private String price;
	
	protected void showName() {
		System.out.println(this.getName());
	}
	
	protected void showPrice() {
		System.out.println(this.getPrice());
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
