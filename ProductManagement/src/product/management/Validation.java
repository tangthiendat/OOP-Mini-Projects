package product.management;

import java.util.Scanner;

public class Validation {
	static Scanner sc = new Scanner(System.in);
	
	public static int inputInt(String inputMess, int min, int max, String errorMess) {
		int number;
		do {
			System.out.print(inputMess);
			String input = sc.nextLine();
			try {
				number = Integer.parseInt(input);
				if(number < min || number > max)
					System.err.println("Please enter an integer from " + min + " to " + max + ".");
			}
			catch(Exception e) {
				number = max+1;
				System.err.println(errorMess);
			}
		}while(number < min || number > max);
		return number;
	}
	
	public static double inputDouble(String inputMess, double min, double max, String errorMess) {
		double number;
		do {
			System.out.print(inputMess);
			String input = sc.nextLine();
			try {
				number = Double.parseDouble(input);
				if(number < min || number > max)
					System.err.println("Please enter an real number from " + min + " to " + max + ".");
			}
			catch(Exception e) {
				number = max+1;
				System.err.println(errorMess);
			}
		}while(number < min || number > max);
		return number;
	}
	
	public static String inputString(String inputMess, String regex, String errorMess) {
		String input;
		do {
			System.out.print(inputMess);
			input = sc.nextLine();
			if(!input.matches(regex))
				System.err.println(errorMess);
		}while(!input.matches(regex));
		return input;
	}
}
