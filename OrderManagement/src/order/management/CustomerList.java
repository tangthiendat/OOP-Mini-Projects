package order.management;

import java.io.*;
import java.util.ArrayList;

public class CustomerList {
	private ArrayList<Customer> customers;

	public CustomerList() {
		customers = new ArrayList<>();
	}

	public void loadCustomersFromFile() {
		String projectDirectory = System.getProperty("user.dir");
		File file = new File(projectDirectory + "\\data\\customers.txt");
		if (file.isFile()) {
			try {
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				String line = new String();
				while (true) {
					line = br.readLine();
					if (line == null)
						break;
					String info[] = line.split("\\,");
					String customerID = info[0];
					String customerName = info[1];
					String customerAddress = info[2];
					String customerPhone = info[3];
					customers.add(new Customer(customerID, customerName, customerAddress, customerPhone));
				}
				fr.close();
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void saveCustomersToFile() {
		String projectDirectory = System.getProperty("user.dir");
		File file = new File(projectDirectory + "\\data\\customers.txt");
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			for(Customer updatedCustomer : customers) {
				bw.write(updatedCustomer.toString());
				bw.newLine();
			}
			bw.close();
			System.out.println("Data saved!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void listAllCustomers() {
		if(!customers.isEmpty()) {
			System.out.printf("%-8s%-20s%-15s%-15s\n", "ID", "Customer name", "Address", "Phone");
			for (Customer customer : customers)
				System.out.printf("%-8s%-20s%-15s%-15s\n", customer.getCustomerID(), customer.getCustomerName(),
						customer.getCustomerAddress(), customer.getCustomerPhone());
		}
	}

	public void addNewCustomer() {
		while (true) {
			String customerID = new String();
			Customer duplicateCustomer = new Customer();
			do {
				customerID = Validation.inputString("Enter customer ID: ", "[\\w]+", "Ivalid customer ID. Please enter again.");
				duplicateCustomer = findCustomerByID(customerID);
				if (duplicateCustomer != null)
					System.err.println("This ID exists.");
			} while (duplicateCustomer != null);
			String customerName = Validation.inputString("Enter customer name: ", "[\\w\\s]+","Invalid customer name. Please enter again.");
			String customerAddress = Validation.inputString("Enter customer address: ", "[\\w\\s]+","Invalid customer address. Please enter again.");
			String customerPhone = Validation.inputString("Enter customer phone number: ", "\\d{10,12}", "Customer phone number must contain 10-12 numbers.");
			customers.add(new Customer(customerID, customerName, customerAddress, customerPhone));
			String answer = Validation.inputString("Do you want to continue (Y/N)? Choose Y to continue, choose N to return to the menu.\n", "(Y|y|N|n)", "Please enter Y/y or N/n.");
			if (answer.equalsIgnoreCase("N"))
				break;
		}
	}

	public void searchCustomerByID() {
		String customerID = Validation.inputString("Enter customer ID: ", "[\\w]+", "Ivalid customer ID. Please enter again.");
		Customer chosenCustomer = findCustomerByID(customerID);
		if (chosenCustomer == null) {
			System.err.println("This customer does not exist.");
		} else {
			chosenCustomer.showInfo();
		}
	}

	public Customer findCustomerByID(String customerID) {
		for (Customer customer : customers)
			if (customer.getCustomerID().equalsIgnoreCase(customerID))
				return customer;
		return null;
	}

	public void updateCustomerInfo() {
		String customerID = Validation.inputString("Enter customer ID: ", "[\\w]+", "Ivalid customer ID. Please enter again.");
		Customer chosenCustomer = findCustomerByID(customerID);
		if (chosenCustomer == null) {
			System.err.println("This customer does not exist.");
		} else {
			chosenCustomer.showInfo();
			String newCustomerName = Validation.inputString("Enter new customer name: ", "([\\s]{0,}|[\\w\\s]+)", "Invalid customer name. Please enter again.");
			String newCustomerAddress = Validation.inputString("Enter new customer address: ", "([\\s]{0,}|[\\w\\s]+)",	"Invalid customer address. Please enter again.");
			String newCustomerPhone = Validation.inputString("Enter new customer phone number: ", "([\\s]{0,}|\\d{10,12})",	"Customer phone number must contain 10-12 numbers.");
			String confirmToUpdate = Validation.inputString("Are you sure you want to update this customer (Y/N)? Choose Y to update, choose N to return to the menu.\n", "(Y|y|N|n)", "Please enter Y/y or N/n.");
			if (confirmToUpdate.equalsIgnoreCase("Y")) {
				if (!newCustomerName.isBlank())
					chosenCustomer.setCustomerName(newCustomerName);
				if (!newCustomerAddress.isBlank())
					chosenCustomer.setCustomerAddress(newCustomerAddress);
				if (!newCustomerPhone.isBlank())
					chosenCustomer.setCustomerPhone(newCustomerPhone);
				System.out.println("Success!");
			} else {
				System.err.println("Fail!");
			}
		}
	}
}
