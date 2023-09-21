package order.management;


public class Main {

	public static void main(String[] args) {
		ProductList productList = new ProductList();
		CustomerList customerList = new CustomerList();
		OrderList orderList = new OrderList();
		productList.loadProductsFromFile();
		customerList.loadCustomersFromFile();
		orderList.loadOrdersFromFile();
		while(true) {
			System.out.println("WELCOME TO ORDER MANAGEMENT");
			System.out.println("""
                    \t1. List all Products
                    \t2. List all Customers
                    \t3. Search a Customer by ID
                    \t4. Add a Customer
                    \t5. Update a Customer
                    \t6. Save Customers to file
                    \t7. List all Orders in ascending order of customer name
                    \t8. List all pending Orders
                    \t9. Add an Order
                    \t10. Update an Order
                    \t11. Save Orders to file
                    \t12. Quit""");
			int option = Validation.inputInt("Enter option: ", 1, 12, "Invalid option. Please enter an integer from 1 to 7.");
			switch(option) {
			case 1:
				productList.listAllProducts();
				break;
			case 2:
				customerList.listAllCustomers();
				break;
			case 3:
				customerList.searchCustomerByID();
				break;
			case 4:
				customerList.addNewCustomer();
				break;
			case 5:
				customerList.updateCustomerInfo();
				break;
			case 6:
				customerList.saveCustomersToFile();
				break;
			case 7:
				orderList.listAllOrders();
				break;
			case 8:
				orderList.listAllPendingOrders();
				break;
			case 9:
				orderList.addNewOrder();
				break;
			case 10:
				System.out.println("\t\tUPDATE");
				System.out.println("\t1. Update order information\n"
								 + "\t2. Delete order information");
				int choice = Validation.inputInt("Enter suboption: ", 1, 2, "Invalid option. Please enter an integer from 1 to 2.");
				if(choice == 1) {
					orderList.updateOrderInfo();
				}else {
					orderList.deleteOrderInfo();
				}
				break;
			case 11:
				orderList.saveOrdersToFile();
				break;
			case 12:
				return;
			}
		}
	}

}
