import java.util.ArrayList;

public class FoodList {
	private final ArrayList <Food> food_list;

	public FoodList() {
		food_list = new ArrayList<>();
	}
	
	public Food findFoodByID(String id) {
		for(Food food : food_list) {
			if(food.getId().equalsIgnoreCase(id))
				return food;
		}
		return null;
	}
	
	public void addNewFood() {
		while(true) {
			String id;
			Food duplicateFood;
			do {
				id = Validation.inputString("Enter food ID: ", "[A-Za-z0-9\\s]+", "Invalid ID. Please enter again.");
				duplicateFood = findFoodByID(id);
				if(duplicateFood != null)
					System.err.println("ID can't be duplicate.");
			}while(duplicateFood != null);
					
			String name = Validation.inputString("Enter food name: ", "[A-Za-z\\s]+", "Invalid food name. Please enter again.");
			double weight = Validation.inputPositiveRealNumber("Enter weight: ", "Invalid weight. Please enter a positive real number.");
			String type = Validation.inputString("Enter type: ", "[A-Za-z\\s]+", "Invalid type. Please enter again.");
			String place = Validation.inputString("Enter place: ", "(Ngan mat|Ngan dong)", "There are only two places: Ngan mat, Ngan dong. Please enter again.");
			String expiredDate = Validation.inputDate("Enter expired date: ", "dd-MM-yyyy");
			food_list.add(new Food(id, name, weight, type, place, expiredDate));
            String confirmToContinue = Validation.inputString("Do you want to continue(Y/N)? Choose Y to continue adding another food, choose N to return to the menu.\n", "(Y|y|N|n)", "Please enter Y/y or N/n.");
            if(confirmToContinue.equalsIgnoreCase("N"))
                break;
        }
	}
	
	public void searchFoodByName() {
		if(food_list.isEmpty()) {
			System.err.println("No food.");			
		}else {
			while(true) {
				String name = Validation.inputString("Enter food name: ", "[A-Za-z\\s]+", "Invalid food name. Please enter again.");
				System.out.println("List of food found: ");
				System.out.printf("%-6s%-20s%-8s%-12s%-12s%-12s\n", "ID", "Name", "Weight", "Type", "Place", "Expired Date");
				for(Food food : food_list)
					if(food.getName().toLowerCase().contains(name.toLowerCase()))
						System.out.printf("%-6s%-20s%-8.1f%-12s%-12s%-12s\n", food.getId(), food.getName(), food.getWeight(), food.getType(), food.getPlace(), food.getExpiredDate());
				String confirmToContinue = Validation.inputString("Do you want to continue(Y/N)? Choose Y to continue searching for another food, choose N to return to the menu.\n", "(Y|y|N|n)", "Please enter Y/y or N/n.");
				if(confirmToContinue.equalsIgnoreCase("N"))
					break;
			}
		}
	}
	
	public void removeFood() {
		if(food_list.isEmpty()) {
			System.err.println("No food.");			
		}else {
			String id = Validation.inputString("Enter food ID: ", "[A-Za-z0-9\\s]+", "Invalid ID. Please enter again.");
			Food chosenFood = findFoodByID(id);
			if(chosenFood == null)
				System.err.println("This food does not exist.");
			else {
				System.out.println("Food found: ");
				System.out.printf("%-6s%-20s%-8s%-12s%-12s%-12s\n", "ID", "Name", "Weight", "Type", "Place", "Expired Date");
				System.out.printf("%-6s%-20s%-8.1f%-12s%-12s%-12s\n", chosenFood.getId(), chosenFood.getName(), chosenFood.getWeight(), chosenFood.getType(),chosenFood.getPlace(), chosenFood.getExpiredDate());
				String confirmToDelete = Validation.inputString("Are you sure you want to delete this food (Y/N)? Choose Y to delete or choose N to cancel.\n", "(Y|y|N|n)", "Please enter Y/y or N/n.");
				if(confirmToDelete.equalsIgnoreCase("Y")) {
					food_list.remove(chosenFood);
					System.out.println("Success!");
				}else
					System.err.println("Fail!");
			}
		}
		
	}
	
	public void printFoodListDescByExpiredDate() {
		if(food_list.isEmpty()) {
			System.err.println("No food.");			
		}
		else {
			food_list.sort((food1, food2) -> {
                int day1 = Integer.parseInt(food1.getExpiredDate().split("-")[0]);
                int month1 = Integer.parseInt(food1.getExpiredDate().split("-")[1]);
                int year1 = Integer.parseInt(food1.getExpiredDate().split("-")[2]);

                int day2 = Integer.parseInt(food2.getExpiredDate().split("-")[0]);
                int month2 = Integer.parseInt(food2.getExpiredDate().split("-")[1]);
                int year2 = Integer.parseInt(food2.getExpiredDate().split("-")[2]);

                return String.format("%s-%s-%s", year2, month2, day2).compareTo(String.format("%s-%s-%s", year1, month1, day1));
//					if(year2 > year1)
//						return 1;
//					else if(year2 < year1)
//						return -1;
//					else {
//						if(month2 > month1)
//							return 1;
//						else if(month2 < month1)
//							return -1;
//						else {
//							if(day2 > day1)
//								return 1;
//							else if(day2 < day1)
//								return -1;
//							else
//								return 0;
//						}
//					}
            });
			System.out.printf("%-6s%-20s%-8s%-12s%-12s%-12s\n", "ID", "Name", "Weight", "Type", "Place", "Expired Date");
			for(Food food : food_list)
				System.out.printf("%-6s%-20s%-8.1f%-12s%-12s%-12s\n", food.getId(), food.getName(), food.getWeight(), food.getType(), food.getPlace(), food.getExpiredDate());
		}
		
	}

	//Sample data
	public void generateFood() {
		food_list.add(new Food("F001", "Thit heo", 2.0, "Thit", "Ngan dong", "11-07-2023"));
		food_list.add(new Food("F002", "Ca hoi", 0.7, "Ca", "Ngan mat", "29-05-2023"));
		food_list.add(new Food("F003", "Trung ga", 0.6, "Trung", "Ngan mat", "05-06-2023"));
		food_list.add(new Food("F004", "Bap cai", 0.5, "Rau cu", "Ngan mat", "01-06-2023"));
		food_list.add(new Food("F005", "Cai be dun", 0.8, "Rau cu", "Ngan mat", "24-05-2023"));
		food_list.add(new Food("F006", "Sua tuoi", 0.2, "Do uong", "Ngan mat", "22-10-2023"));
		food_list.add(new Food("F007", "Bia Tiger", 0.3, "Do uong", "Ngan mat", "10-08-2024"));
		food_list.add(new Food("F008", "Xuc xich", 1.0, "Thit", "Ngan dong", "08-07-2023"));
		food_list.add(new Food("F009", "Chuoi", 0.5, "Trai cay", "Ngan mat", "26-05-2023"));
		food_list.add(new Food("F010", "Nho", 0.7, "Trai cay", "Ngan mat", "01-06-2023"));
	}
}
