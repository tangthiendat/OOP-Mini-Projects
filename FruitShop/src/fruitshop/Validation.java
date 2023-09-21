package fruitshop;

import java.util.Scanner;

public class Validation {
	static Scanner sc = new Scanner(System.in);
	
	//get a number between min and max
	public static int inputInt(String inputMess, int min, int max, String errorMess) {
		int number = 0;
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
	
	public static int inputPositiveInteger(String inputMess, String errorMess) {
		int number = 0;
		do {
			System.out.print(inputMess);
			String input = sc.nextLine();
			try {
				number = Integer.parseInt(input);
				if(number < 0.0f) {
					System.err.println("Please enter a positive integer.");
				}
			}
			catch(NumberFormatException e) {
				number = -1;
				System.err.println(errorMess);
			}
		}while(number < 0);
		return number;
	}
	
	//get non-empty string
	public static String inputString(String inputMess, String regex, String errorMess) {
		String input = new String();
		do {
			System.out.print(inputMess);
			input = sc.nextLine();
			if(!input.matches(regex))
				System.err.println(errorMess);
		}while(!input.matches(regex));
		return input;
	}

}
