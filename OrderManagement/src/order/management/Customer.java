package order.management;

public class Customer {
	private String customerID, customerName, customerAddress, customerPhone;
	
	public Customer() {
		customerID = "";
		customerName = "";
		customerAddress = "";
		customerPhone = "";
	}
	
	public Customer(Customer customer) {
		customerID = customer.customerID;
		customerName = customer.customerName;
		customerAddress = customer.customerAddress;
		customerPhone = customer.customerPhone;
	}

	public Customer(String customerID, String customerName, String customerAddress, String customerPhone) {
		this.customerID = customerID;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.customerPhone = customerPhone;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	
	public String toString() {
		return customerID + "," + customerName + "," + customerAddress + "," + customerPhone;
	}
	
	public void showInfo() {
		System.out.printf("%-8s%-25s%-20s%-12s\n", "ID", "Customer name", "Customer address", "Phone");
		System.out.printf("%-8s%-25s%-20s%-12s\n", customerID, customerName, customerAddress, customerPhone);
	}
}
