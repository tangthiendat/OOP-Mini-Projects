package order.management;

public class Product {
	private String productID, productName, unit, origin, price;
	
	public Product() {
		productID = new String();
		productName = new String();
		unit = new String();
		origin = new String();
		price = new String();
	}
	
	public Product(Product product){
		productID = new String(product.productID);
		productName = new String(product.productName);
		unit = new String(product.unit);
		origin = new String(product.origin);
		price = new String(product.price);
	}

	public Product(String productID, String productName, String unit, String origin, String price) {
		this.productID = productID;
		this.productName = productName;
		this.unit = unit;
		this.origin = origin;
		this.price = price;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	public String toString() {
		return productID + "," + productName + "," + unit + "," + origin + "," + price;
	}
}
