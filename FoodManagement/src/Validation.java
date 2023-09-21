import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class Validation {
	static Scanner sc = new Scanner(System.in);
	//get a number between min and max
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
	
	public static double inputPositiveRealNumber(String inputMess, String errorMess) {
		double number;
		do {
			System.out.print(inputMess);
			String input = sc.nextLine();
			try {
				number = Float.parseFloat(input);
				if(number < 0.0f) {
					System.err.println("Please enter a positive real number.");
				}
			}
			catch(Exception e) {
				number = -1.0;
				System.err.println(errorMess);
			}
		}while(number < 0);
		return number;
	}
	
	//get non-empty string
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
	
	
	//get valid date with your format
	public static String inputDate(String mess, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		dateFormat.setLenient(false);
		while(true) {
			System.out.print(mess);
			String input = sc.nextLine();
			try {
				Date date = dateFormat.parse(input);
				return dateFormat.format(date);
			} catch (Exception e) {
				System.err.println("Please enter valid day with format " + format + ".");
			}
		}
	}
}
