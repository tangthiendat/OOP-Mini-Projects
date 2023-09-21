public class Main {
	public static void main(String[] args) {
		FoodList foodList = new FoodList();
		foodList.generateFood();
		while(true) {
			System.out.println("WELCOME TO FOOD MANAGEMENT\n"
					 		 + "Select the options below:");
			System.out.println("""
                    \t1. Add a new food
                    \t2. Search a food by name
                    \t3. Remove the food by ID
                    \t4. Print the food list in the descending order of expired date
                    \t5. Quit""");
			int option = Validation.inputInt("Enter option: ", 1, 5, "Invalid option. Please enter an integer.");
			switch(option) {
			case 1:
				foodList.addNewFood();
				break;
			case 2:
				foodList.searchFoodByName();
				break;
			case 3:
				foodList.removeFood();
				break;
			case 4:
				foodList.printFoodListDescByExpiredDate();
				break;
			case 5:
				return;
			}
		}
	}
}
