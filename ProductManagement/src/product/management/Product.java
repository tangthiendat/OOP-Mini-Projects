package product.management;

import java.io.Serial;
import java.io.Serializable;

public class Product implements Serializable{
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 1L;
	private String productID, productName, status;
	private double unitPrice;
	private int quantity;
	
	public Product() {
		this.productID = "";
		this.productName = "";
		this.unitPrice = 0;
		this.quantity = 0;
		this.status = "";
	}
	
	public Product(String productID, String productName, double unitPrice, int quantity, String status) {
		this.productID = productID;
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.status = status;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void showInfo() {
		System.out.printf("%-10s%-15s%-14s%-12s%-15s\n", "ID", "Product Name", "Unit Price", "Quantity", "Status");
		System.out.printf("%-10s%-15s$%-13.2f%-12d%-15s\n", productID, productName, unitPrice, quantity, status);
	}
}
