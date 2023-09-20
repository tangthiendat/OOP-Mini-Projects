package product.management;

public class Main {
	public static void main(String[] args) {
		ProductList productList = new ProductList();
		while(true) {
			System.out.println("WELCOME TO PRODUCT MANAGEMENT");
			System.out.println("""
                    \t1. Create a product
                    \t2. Check exist product
                    \t3. Search product information by name
                    \t4. Update product
                    \t5. Save products
                    \t6. Print all products
                    \t7. Quit""");
			int option = Validation.inputInt("Enter option: ", 1, 7, "Invalid option. Please enter an integer from 1 to 7.");
			switch(option) {
			case 1:
				productList.createProduct();
				break;
			case 2:
				productList.checkExistProduct();
				break;
			case 3:
				productList.searchProductInfoByName();
				break;
			case 4:
				System.out.println("\t\tUPDATE");
				System.out.println("\t1. Update product information\n"
								 + "\t2. Delete product information");
				int choice = Validation.inputInt("Enter suboption: ", 1, 2, "Invalid option. Please enter an integer from 1 to 2.");
				if(choice == 1) {
					productList.updateProductInfo();
				}else {
					productList.deleteProductInfo();
				}
				break;
			case 5:
				productList.saveData();
				System.out.println("Data saved!");
				break;
			case 6:
				productList.printAllProducts();
				break;
			case 7:
				return;
			}
		}
	}
}