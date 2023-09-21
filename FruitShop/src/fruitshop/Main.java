package fruitshop;

public class Main {

	public static void main(String[] args) {
		FruitShop fruitShop = new FruitShop();
		fruitShop.generateFruit();
		while(true) {
			System.out.println("FRUIT SHOP SYSTEM");
			System.out.println("""
                    \t1. Create Fruit
                    \t2. View orders
                    \t3. Shopping (for buyer)
                    \t4. Exit""");
			System.out.println("(Please choose 1 to create product, 2 to view order, 3 for shopping, 4 to Exit program).");
			int option = Validation.inputInt("Enter option: ", 1, 4, "Invalid option. Please enter an integer number.");
			switch(option) {
				case 1:
					fruitShop.createFruit();
					break;
				case 2:
					fruitShop.viewOrders();
					break;
				case 3:
					fruitShop.shopping();
					break;
				case 4: 
					return;
			}
		}
	}

}
