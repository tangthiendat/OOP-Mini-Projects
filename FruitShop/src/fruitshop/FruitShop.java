package fruitshop;

import java.util.ArrayList;
import java.util.Hashtable;

public class FruitShop {
	private final ArrayList<Fruit> fruits;
	
	public FruitShop(){
		fruits = new ArrayList<>();

	}
	
	public boolean isIDExist(String id) {
		for(Fruit fruit : fruits) {
			if(fruit.getId().equalsIgnoreCase(id))
				return true;
		}
		return false;
	}
	
	public void createFruit() {
		while(true) {
			if(!fruits.isEmpty()) {
				String confirmToContinue = Validation.inputString("Do you want to continue (Y/N)? Choose Y to continue, choose N to display all fruits created and return to main screen.\n", "(Y|y|N|n)", "Please enter Y/y or N/n.");
				if(confirmToContinue.equalsIgnoreCase("N")) {
					System.out.println("All fruits created: ");
					System.out.printf("%-6s%-15s%-9s%-10s\n", "ID", "Fruit name", "Price", "Origin");
					for(Fruit fruit : fruits)
						System.out.printf("%-6s%-15s%4d$    %-10s\n", fruit.getId(), fruit.getFruitName(), fruit.getPrice(), fruit.getOrigin());
					break;
				}
					
			}
			String id = Validation.inputString("Enter ID: ", "[A-Za-z0-9\\s]+", "Invalid ID. Please enter again.");
			if(isIDExist(id)) {
				System.err.println("ID can't be duplicated.");
			} else {
				String fruitName = Validation.inputString("Enter fruit name: ", "[A-Za-z\\s]+", "Invalid fruit name. Please enter again.");
				int price = Validation.inputPositiveInteger("Enter price ($): ", "Invalid price. Please enter a positive integer.");
				String origin = Validation.inputString("Enter origin: ", "[A-Za-z\\s]+", "Invalid origin. Please enter again.");
				fruits.add(new Fruit(id, fruitName, price, 0, origin));
			}
		}
	}
	
	public void printOrderDetails(ArrayList <Fruit> orderedFruits) {
		int totalMoney = 0;
		System.out.printf("%-12s%-12s%-9s%-8s\n", "Product", "Quantity", "Price", "Amount");
		for(Fruit orderedFruit : orderedFruits) {
			System.out.printf("%-12s   %-9d%2d$       %2d$\n", orderedFruit.getFruitName(), orderedFruit.getQuantity(), orderedFruit.getPrice(), orderedFruit.getQuantity()*orderedFruit.getPrice());
			totalMoney += orderedFruit.getPrice()*orderedFruit.getQuantity();
		}
		System.out.println("Total: " + totalMoney + "$");
	}
	
	Hashtable <String, ArrayList<Fruit>> orders = new Hashtable<>();
	public void showFruitList() {
		System.out.println("List of fruits: ");
		System.out.println("|------|----------------|--------------|---------|");
		System.out.println("| Item |   Fruit name   |    Origin    |  Price  |");
		System.out.println("|------|----------------|--------------|---------|");
		for(Fruit fruit : fruits) {
			System.out.printf("|  %2d  | %-15s| %-13s|  %2d$    |\n", fruits.indexOf(fruit)+1, fruit.getFruitName(), fruit.getOrigin(), fruit.getPrice());
		}
		System.out.println("|------|----------------|--------------|---------|");
	}
	
	public void shopping() {
		if(fruits.isEmpty()) {
			System.err.println("No fruits.");
		} else {
			//Order
			ArrayList <Fruit> orderedFruits = new ArrayList<>();
			while(true) {
				showFruitList();
				int choice = Validation.inputInt("Select item: ", 1, fruits.size(), "Invalid item. Please enter an integer.");
				Fruit chosenFruit = new Fruit(fruits.get(choice-1));
				System.out.println("You selected: " + chosenFruit.getFruitName());
				int quantity = Validation.inputPositiveInteger("Please input quantity: ", "Invalid quantity. Please enter a positive integer number.");
				chosenFruit.setQuantity(quantity);
				orderedFruits.add(chosenFruit);
				String confirmToFinish = Validation.inputString("Do you want to order now (Y/N)? Choose Y to finish ordering, choose N to continue ordering.\n", "(Y|y|N|n)", "Please enter Y/y or N/n.");
				if(confirmToFinish.equalsIgnoreCase("Y")) {
					printOrderDetails(orderedFruits);
					String customerName = Validation.inputString("Input your name: ", "[A-Za-z\\s]+", "Invalid name. Please enter again.");
					orders.put(customerName, orderedFruits);
					break;
				}
			}
		}
	}
	
	public void viewOrders() {
		if(orders.isEmpty()) {
			System.err.println("No orders.");
		}else {
			for(String customerName : orders.keySet()) {
				System.out.println("Customer: " + customerName);
				printOrderDetails(orders.get(customerName));
			}
		}
	}
	
	public void generateFruit() {
		fruits.add(new Fruit("F001", "Coconut", 3, 0, "Vietnam"));
		fruits.add(new Fruit("F002", "Orange", 2, 0, "US"));
		fruits.add(new Fruit("F003", "Apple", 3, 0, "Thailand"));
		fruits.add(new Fruit("F004", "Grape", 6, 0, "France"));
		fruits.add(new Fruit("F005", "Jackfruit", 4, 0, "Vietnam"));
		fruits.add(new Fruit("F006", "Mango", 3, 0, "China"));
		fruits.add(new Fruit("F007", "Banana", 2, 0, "US"));
		fruits.add(new Fruit("F008", "Kiwi", 5, 0, "New Zealand"));
		fruits.add(new Fruit("F009", "Strawberry", 5, 0, "US"));
		fruits.add(new Fruit("F010", "Durian", 8, 0, "Vietnam"));
	}
}
