package order.management;

import java.util.ArrayList;
import java.io.*;

public class ProductList {
	private final ArrayList <Product> products;
	public ProductList() {
		products = new ArrayList<>();
	}

	public Product findProductByID(String productID) {
		for(Product product : products)
			if(product.getProductID().equalsIgnoreCase(productID))
				return product;
		return null;
	}

	public void loadProductsFromFile() {
		String projectDirectory = System.getProperty("user.dir");
		File file = new File(projectDirectory + "\\data\\products.txt");
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
					String productID = info[0];
					String productName = info[1];
					String unit = info[2];
					String origin = info[3];
					String price = info[4];
					products.add(new Product(productID, productName, unit, origin, price));
				}
				fr.close();
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void listAllProducts() {
		if(!products.isEmpty()) {
			System.out.printf("%-8s%-25s%-25s%-25s%-10s\n", "ID", "Product name", "Unit", "Origin", "Price");
			for(Product product : products)
				System.out.printf("%-8s%-25s%-25s%-25s%-10s\n", product.getProductID(), product.getProductName(),
								product.getUnit(), product.getOrigin(), product.getPrice());
		}
	}
}
