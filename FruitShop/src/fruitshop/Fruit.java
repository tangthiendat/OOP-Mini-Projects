package fruitshop;

public class Fruit {
	private String id, fruitName, origin;
	private int price, quantity;

	public Fruit(Fruit fruit) {
		id = fruit.id;
		fruitName = fruit.fruitName;
		price = fruit.price;
		quantity = fruit.quantity;
		origin = fruit.origin;
	}
	
	public Fruit(String id, String fruitName, int price, int quantity, String origin) {
		this.id = id;
		this.fruitName = fruitName;
		this.price = price;
		this.quantity = quantity;
		this.origin = origin;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFruitName() {
		return fruitName;
	}
	public void setFruitName(String fruitName) {
		this.fruitName = fruitName;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
