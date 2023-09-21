package order.management;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class OrderList {
	private final ArrayList<Order> orders;
	
	public OrderList() {
		orders = new ArrayList<>();
	}
	
	public void loadOrdersFromFile() {
		String projectDirectory = System.getProperty("user.dir");
		File file = new File(projectDirectory + "\\data\\orders.txt");
		CustomerList customerList = new CustomerList();
		ProductList productList = new ProductList();
		customerList.loadCustomersFromFile();
		productList.loadProductsFromFile();
		if(file.isFile()) {
			try {
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				String line;
				while(true) {
					line = br.readLine();
					if(line == null)
						break;
					String[] info = line.split(",");
					String orderID = info[0];
					String customerID = info[1];
					String productID = info[2];
					int orderQuantity = Integer.parseInt(info[3]);
					String orderDate = info[4];
					boolean status = Boolean.parseBoolean(info[5]);
					orders.add(new Order(orderID, customerList.findCustomerByID(customerID), productList.findProductByID(productID), orderQuantity, orderDate, status));
				}				
				fr.close();
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void listAllOrders() {
		ArrayList <Order> orderList = new ArrayList<>(orders);
		orderList.sort(Comparator.comparing((Order order) -> order.getCustomer().getCustomerName()));
		System.out.printf("%-11s%-20s%-15s%-15s%-25s%-25s%-12s%-12s%-15s%-10s\n", "Order ID", 
						"Customer Name","Address", "Phone",
						"Product Name", "Unit", "Price",
						"Quantity", "Date", "Status");
		for(Order order : orderList)
			System.out.printf("%-11s%-20s%-15s%-15s%-25s%-25s%-12s%-12d%-15s%-10s\n", order.getOrderID(), 
					order.getCustomer().getCustomerName(), order.getCustomer().getCustomerAddress(), order.getCustomer().getCustomerPhone(),
					order.getProduct().getProductName(), order.getProduct().getUnit(), order.getProduct().getPrice(),
					order.getOrderQuantity(), order.getOrderDate(), order.getStatus());
	}
	
	public void listAllPendingOrders() {
		ArrayList <Order> orderList = new ArrayList<>(orders);
		System.out.printf("%-11s%-20s%-15s%-15s%-25s%-25s%-12s%-12s%-15s%-10s\n", "Order ID", 
				"Customer Name","Address", "Phone",
				"Product Name", "Unit", "Price",
				"Quantity", "Date", "Status");
		for(Order order : orderList)
			if(!order.getStatus())
				System.out.printf("%-11s%-20s%-15s%-15s%-25s%-25s%-12s%-12d%-15s%-10s\n", order.getOrderID(), 
						order.getCustomer().getCustomerName(), order.getCustomer().getCustomerAddress(), order.getCustomer().getCustomerPhone(),
						order.getProduct().getProductName(), order.getProduct().getUnit(),order.getProduct().getPrice(),
						order.getOrderQuantity(), order.getOrderDate(), order.getStatus());
	}
	
	public Order findOrderByID(String orderID) {
		for(Order order : orders)
			if(order.getOrderID().equalsIgnoreCase(orderID))
				return order;
		return null;
	}
	
	public void addNewOrder() {
		CustomerList customerList = new CustomerList();
		ProductList productList = new ProductList();
		customerList.loadCustomersFromFile();
		productList.loadProductsFromFile();
		while(true) {
			String orderID;
			Order duplicateOrder;
			do {
				orderID = Validation.inputString("Choose order ID: ", "[\\w]+", "Invalid ID. Please enter again.");
				duplicateOrder = findOrderByID(orderID);
				if(duplicateOrder != null)
					System.err.println("This order ID exists.");
			}while(duplicateOrder != null);
			System.out.println("List of customers: ");
			customerList.listAllCustomers();
			String customerID;
			Customer chosenCustomer;
			do {
				customerID = Validation.inputString("Choose customer ID: ", "[\\w]+", "Invalid ID. Please enter again.");
				chosenCustomer = customerList.findCustomerByID(customerID);
				if(chosenCustomer == null)
					System.err.println("This customer does not exist.");
			}while(chosenCustomer == null);
			System.out.println("List of products: ");
			productList.listAllProducts();
			String productID;
			Product chosenProduct;
			do {
				productID = Validation.inputString("Choose product ID: ", "[\\w]+", "Invalid ID. Please enter again.");
				chosenProduct = productList.findProductByID(productID);
				if(chosenProduct == null)
					System.err.println("This product does not exist.");
			}while(chosenProduct == null);
			int quantity = Validation.inputPositiveInteger("Enter quantity: ", "Invalid quantity. Please enter a positive integer.");
			String date = Validation.inputString("Enter date: ", "\\d{1,2}/\\d{1,2}/\\d{4}", "Please enter valid date with format: MM/dd/yyyy.");
			String status = Validation.inputString("Enter status: ", "(true|false)", "There are only two status: 'true' and 'false'. Please enter again.");
			orders.add(new Order(orderID, chosenCustomer, chosenProduct, quantity, date, Boolean.parseBoolean(status)));
			String confirmToContinue = Validation.inputString("Do you want to continue (Y/N)? Choose Y to continue, chose N to return to the menu.\n", "(Y|y|N|n)", "Please enter Y/y or N/n.");
			if(confirmToContinue.equalsIgnoreCase("N"))
				break;
		}
	}
	
	public void saveOrdersToFile() {
		String projectDirectory = System.getProperty("user.dir");
		File file = new File(projectDirectory + "\\data\\orders.txt");
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			for(Order updatedOrder : orders) {
				bw.write(updatedOrder.toString());
				bw.newLine();
			}
			bw.close();
			System.out.println("Data saved!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateOrderInfo() {
		String orderID = Validation.inputString("Enter order ID: ", "[\\w]+", "Invalid ID. Please enter again.");
		Order chosenOrder = findOrderByID(orderID);
		if(chosenOrder == null) {
			System.err.println("Order does not exist.");
		}else {
			chosenOrder.showInfo();
			String status = Validation.inputString("Enter new status: ", "([\\s]{0,}|true|false)", "There are only two status: 'true' and 'false'. Please enter again.");
			String confirmToUpdate = Validation.inputString("Are you sure you want to update this order (Y/N)? Choose Y to update, choose N to return to the menu.\n", "(Y|y|N|n)", "Please enter Y/y or N/n.");
			if(confirmToUpdate.equalsIgnoreCase("Y")) {
				if(!status.isBlank())
					chosenOrder.setStatus(Boolean.parseBoolean(status));
				System.out.println("Success!");
			}else {
				System.err.println("Fail!");
			}
		}
	}
	
	public void deleteOrderInfo() {
		String orderID = Validation.inputString("Enter order ID: ", "[\\w]+", "Invalid ID. Please enter again.");
		Order chosenOrder = findOrderByID(orderID);
		if(chosenOrder == null) {
			System.err.println("Order does not exist.");
		}else {
			chosenOrder.showInfo();
			String confirmToDelete = Validation.inputString("Are you sure you want to delete this order (Y/N)? Choose Y to update, choose N to return to the menu.\n", "(Y|y|N|n)", "Please enter Y/y or N/n.");
			if(confirmToDelete.equalsIgnoreCase("Y")) {
				orders.remove(chosenOrder);
				System.out.println("Success!");
			}else {
				System.err.println("Fail!");
			}
		}
	}
	

}
