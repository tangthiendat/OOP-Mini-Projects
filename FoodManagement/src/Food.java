public class Food {
	private String id, name, type, place, expiredDate;
	private double weight;
	
	public Food() {
		id = "";
		name = "";
		weight = 0;
		type = "";
		place = "";
		expiredDate = "";
	}
	
	public Food(String id, String name, double weight, String type, String place, String expiredDate) {
		super();
		this.id = id;
		this.name = name;
		this.weight = weight;
		this.type = type;
		this.place = place;
		this.expiredDate = expiredDate;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
}
