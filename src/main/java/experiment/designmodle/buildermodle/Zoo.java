package experiment.designmodle.buildermodle;

public class Zoo {
	private String tiger;
	private String panda;
	private String cat;
	private String owl;
	private String dog;
	private String monkey;
	
	public static class Builder {
		private String tiger;
		private String panda;
		private String cat;
		private String owl;
		private String dog;
		private String monkey;
		
		public Builder(String tiger, String panda){
			this.tiger = tiger;
			this.panda = panda;
		}
		
		public Builder cat(String cat) {
			this.cat = cat;
			return this;
		}
		
		public Builder owl(String owl) {
			this.owl = owl;
			return this;
		}
		
		public Builder dog(String dog) {
			this.dog = dog;
			return this;
		}
		
		public Builder monkey(String monkey) {
			this.monkey = monkey;
			return this;
		}
		
		public Zoo build() {
			return new Zoo(this);
		}
		
	}
	
	private Zoo(Builder builder) {
		tiger = builder.tiger;
		panda = builder.panda;
		dog = builder.dog;
		monkey = builder.monkey;
		owl = builder.owl;
		cat = builder.cat;	
	}
	
	public String getTiger() {
		return tiger;
	}
	public void setTiger(String tiger) {
		this.tiger = tiger;
	}
	public String getPanda() {
		return panda;
	}
	public void setPanda(String panda) {
		this.panda = panda;
	}
	public String getCat() {
		return cat;
	}
	public void setCat(String cat) {
		this.cat = cat;
	}
	public String getOwl() {
		return owl;
	}
	public void setOwl(String owl) {
		this.owl = owl;
	}
	public String getDog() {
		return dog;
	}
	public void setDog(String dog) {
		this.dog = dog;
	}
	public String getMonkey() {
		return monkey;
	}
	public void setMonkey(String monkey) {
		this.monkey = monkey;
	}
	
}
