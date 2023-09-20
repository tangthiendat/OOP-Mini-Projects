package product.management;

import java.util.ArrayList;
import java.util.Comparator;

public class ProductList {
	private final ArrayList <Product> newProducts;
	private final ProductDao productDao = new ProductDao();

	public ProductList() {
		newProducts = new ArrayList<>();
	}

	public void createProduct(){
		while(true) {
			String productID = Validation.inputString("Enter product ID: ", "[A-Za-z0-9\\s]+", "Invalid ID. Please enter again.");
			String productName;
			Product duplicateProduct;
			do {
				productName = Validation.inputString("Enter product name: ", "[A-Za-z0-9]{5,}", "Product name must have at least 5 characters (only letters and numbers) and no space.");
				duplicateProduct = productDao.findByName(productName);
				if(duplicateProduct != null) {
					System.err.println("This product exists.");
				} 
			}while(duplicateProduct != null);
			double unitPrice = Validation.inputDouble("Enter unit price: ", 0, 1000, "Invalid unit price. Please enter a real number from 0 to 10000");
			int quantity = Validation.inputInt("Enter quantity: ", 0, 1000, "Invalid quantity. Please enter an integer from 0 to 1000.");
			String status = Validation.inputString("Enter status: ", "(Available|Not Available)", "There are only two status: 'Available' and 'Not Available'. Please enter again.");
			newProducts.add(new Product(productID, productName, unitPrice, quantity, status));
			String confirmToContinue = Validation.inputString("Do you want to continue (Y/N)? Choose Y to continue, chose N to return to the menu.\n", "(Y|y|N|n)", "Please enter Y/y or N/n.");
			if(confirmToContinue.equalsIgnoreCase("N"))
				break;
		}
	}
	

	
	public void checkExistProduct() {
		while(true) {
			String productName = Validation.inputString("Enter product name: ", "[A-Za-z0-9]{5,}", "Product name must have at least 5 characters (only letters and numbers) and no space.");
			if(productDao.findByName(productName) == null)
				System.err.println("No Product Found!");
			else
				System.out.println("Exist Product.");
			String confirmToContinue = Validation.inputString("Do you want to continue (Y/N)? Choose Y to continue, chose N to return to the menu.\n", "(Y|y|N|n)", "Please enter Y/y or N/n.");
			if(confirmToContinue.equalsIgnoreCase("N"))
				break;
		}
	}
	
	public void searchProductInfoByName() {
		while(true) {
			String productName= Validation.inputString("Enter name: ", "[A-Za-z0-9\\s]+" , "Invalid product name. Please enter again.");
			ArrayList <Product> productsFoundByName = productDao.findByNameKeyword(productName);
			if(productsFoundByName.isEmpty()) {
				System.err.println("Have no any product.");
			} else {
				productsFoundByName.sort(Comparator.comparing(Product::getProductName));
				printProductList(productsFoundByName);
			}
			String confirmToContinue = Validation.inputString("Do you want to continue (Y/N)? Choose Y to continue, chose N to return to the menu.\n", "(Y|y|N|n)", "Please enter Y/y or N/n.");
			if(confirmToContinue.equalsIgnoreCase("N"))
				break;
		}
	}
	
	public void saveData(){
		productDao.save(newProducts);
	}
	
	public void updateProductInfo() {
		while(true) {
			String productID = Validation.inputString("Enter product ID: ", "[A-Za-z0-9\\s]+", "Invalid ID. Please enter again.");
			Product selectedProduct = productDao.findByID(productID);
			if(selectedProduct == null) {
				System.err.println("Product name does not exist.");
			} else {
				selectedProduct.showInfo();
				String newProductName = Validation.inputString("Enter new product name: ", "([\\s]{0,}|[A-Za-z0-9]{5,})", "Product name must have at least 5 characters (only letters and numbers) and no space.");
				String newUnitPrice = Validation.inputString("Enter new unit price: ", "([\\s]{0,}|\\d{1,4}\\.\\d{1,}|\\d{1,4}|10000|10000\\.[0]{1,})" , "Invalid unit price. Please enter a real number from 0 to 10000");
				String newQuantity = Validation.inputString("Enter new quantity: ", "([\\s]{0,}|\\d{1,3}|1000)" , "Invalid quantity. Please enter a real number from 0 to 1000");
				String newStatus = Validation.inputString("Enter new status: ", "([\\s]{0,}|(Available|Not Available))", "There are only two status: 'Available' and 'Not Available'. Please enter again.");
				String confirmToUpdate = Validation.inputString("Are you sure you want to update this product (Y/N)? Choose Y to update, choose N to return to the menu.\n", "(Y|y|N|n)", "Please enter Y/y or N/n.");
				if(confirmToUpdate.equalsIgnoreCase("Y")) {
					if(!newProductName.isBlank())
						selectedProduct.setProductName(newProductName);
					if(!newUnitPrice.isBlank())
						selectedProduct.setUnitPrice(Double.parseDouble(newUnitPrice));
					if(!newQuantity.isBlank())
						selectedProduct.setQuantity(Integer.parseInt(newQuantity));
					if(!newStatus.isBlank())
						selectedProduct.setStatus(newStatus);
					productDao.update(selectedProduct);
					System.out.println("Success!");
				} else {
					System.err.println("Fail");
				}
			}
			
			String confirmToContinue = Validation.inputString("Do you want to continue (Y/N)? Choose Y to continue, chose N to return to the menu.\n", "(Y|y|N|n)", "Please enter Y/y or N/n.");
			if(confirmToContinue.equalsIgnoreCase("N"))
				break;
		}
	}
	
	public void deleteProductInfo() {
		while(true) {
			String productID = Validation.inputString("Enter product ID: ", "[A-Za-z0-9\\s]+", "Invalid ID. Please enter again.");
			Product selectedProduct = productDao.findByID(productID);
			if(selectedProduct == null) {
				System.err.println("Product name does not exist.");
			} else {
				selectedProduct.showInfo();
				String confirmToDelete = Validation.inputString("Are you sure you want to delete this product (Y/N)? Choose Y to delete, choose N to return to the menu.\n", "(Y|y|N|n)", "Please enter Y/y or N/n.");
				if(confirmToDelete.equalsIgnoreCase("Y")) {
					productDao.delete(selectedProduct);
					System.out.println("Success!");
				} else {
					System.err.println("Fail");
				}
			}
			String confirmToContinue = Validation.inputString("Do you want to continue (Y/N)? Choose Y to continue, chose N to return to the menu.\n", "(Y|y|N|n)", "Please enter Y/y or N/n.");
			if(confirmToContinue.equalsIgnoreCase("N"))
				break;
		}
	}
	
	public void printProductList(ArrayList <Product> productList) {
		if(!productList.isEmpty()) {
			System.out.printf("%-6s%-10s%-15s%-14s%-12s%-15s\n", "No.", "ID", "Product Name", "Unit Price", "Quantity", "Status");
			for(Product product : productList)
				System.out.printf("%3d   %-10s%-15s$%-13.2f%-12d%-15s\n", productList.indexOf(product)+1 ,product.getProductID(), product.getProductName(), product.getUnitPrice(),product.getQuantity(), product.getStatus());
		}
		
	}
	
	public void printAllProducts() {
		ArrayList <Product> data = productDao.findAll();
		data.sort((product1, product2) -> {
            if (product1.getQuantity() == product2.getQuantity()) {
                return Double.compare(product1.getUnitPrice(), product2.getUnitPrice());
            }
            return product2.getQuantity() - product1.getQuantity();
        });
		printProductList(data);
	}

//	public void generateProduct() {
//		newProducts.add(new Product("PD0001", "Cereal", 5.12, 50, "Available"));
//		newProducts.add(new Product("PD0002", "Ketchup", 1.00, 81, "Available"));
//		newProducts.add(new Product("PD0003", "Mayonnaise", 2.00, 42, "Available"));
//		newProducts.add(new Product("PD0004", "Pasta", 1.84, 50, "Available"));
//		newProducts.add(new Product("PD0005", "Sugar", 1.50, 64, "Available"));
//		newProducts.add(new Product("PD0006", "Biscuits", 1.25, 5, "Not Available"));
//		newProducts.add(new Product("PD0007", "Bread", 1.00, 28, "Available"));
//		newProducts.add(new Product("PD0008", "Juice", 1.50, 10, "Available"));
//		newProducts.add(new Product("PD0009", "Avocado", 0.50, 14, "Available"));
//		newProducts.add(new Product("PD0010", "Flour", 1.50, 7, "Available"));
//	}
}
