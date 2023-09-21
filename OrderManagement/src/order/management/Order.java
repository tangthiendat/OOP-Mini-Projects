package order.management;

public class Order {
	private String orderID, orderDate;
	private Customer customer;
	private Product product;
	private int orderQuantity;
	private boolean status;
	
	public Order() {
		orderID = "";
		customer = new Customer();
		product = new Product();
		orderQuantity = 0;
		status = false;
	}
	
	public Order(String orderID, Customer customer, Product product, int orderQuantity, String orderDate, boolean status) {
		this.orderID = orderID;
		this.customer = new Customer(customer);
		this.product = new Product(product);
		this.orderQuantity = orderQuantity;
		this.orderDate = orderDate;
		this.status = status;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = new Customer(customer);
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = new Product(product);
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String toString() {
		return orderID + "," + customer.getCustomerID() + "," + product.getProductID() + "," + orderQuantity + "," + orderDate + "," + status;
	}
	
	public void showInfo() {
		System.out.printf("%-11s%-20s%-15s%-15s%-25s%-25s%-12s%-12s%-15s%-10s\n", "Order ID", 
				"Customer Name","Address", "Phone",
				"Product Name", "Unit", "Price",
				"Quantity", "Date", "Status");
		System.out.printf("%-11s%-20s%-15s%-15s%-25s%-25s%-12s%-12d%-15s%-10s\n", orderID, 
				customer.getCustomerName(),customer.getCustomerAddress(), customer.getCustomerPhone(),
				product.getProductName(), product.getUnit(), product.getPrice(),
				orderQuantity, orderDate, status);
	}
	
}
