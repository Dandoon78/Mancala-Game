import java.util.Scanner;
import java.util.Random;

public class Utils {
  
	public static char obtainYesNo(String message) {
	    Scanner userInput = new Scanner(System.in);
	    String result; 
	     
	    do {
	    	System.out.print(message);
	    	result = userInput.nextLine().toLowerCase(); 
	    
	    	if (result.equals("y") || result.equals("n"))
	    		break; 
	    	else
	    		System.out.println("Be sure to enter either y or n only.");	
	    } while (true); 
	    return result.charAt(0);
	}
	
    public static double inputDoubleLow(String message,double lowBound) {
      
		double result = 0; 
      
		do {
			result = inputDouble(message);
			if (result < lowBound) 
				System.out.println("Please enter a value larger than or equal to "+lowBound);
			else
				break; 
		} while(true); 
      
		return result; 
      
	} 
    public static double inputDoubleHigh(String message,double highBound) {
		double result = 0; 
		do {
			result = inputDouble(message);
			if (result > highBound) 
				System.out.println("Please enter a value less than or equal to "+highBound);
			else
				break; 
		} while(true); 
		return result; 
	} 
    public static double inputDoubleBetween(String message,
											double lowBound,
											double highBound) {
		double result = 0; 
		
		do {
			result = inputDouble(message);
			if (result < lowBound) 
				System.out.println("Please enter a value larger than or equal to "+lowBound);
			else if (result > highBound) 
				System.out.println("Please enter a value less than or equal to "+highBound);
			else
				break;
		} while (true); 
		return result; 
	} 

    public static int inputIntegerLow(String message,int lowBound) {
		int result = 0; 
		do {
			result = inputInteger(message);
			if (result < lowBound) 
				System.out.println("Please enter a value larger than or equal to "+lowBound);
			else
				break; 
		} while(true); 
		return result; 
	} 
    public static int inputIntegerHigh(String message,int highBound) {
		int result = 0; 
		do {
			result = inputInteger(message);
			if (result > highBound) 
				System.out.println("Please enter a value less than or equal to "+highBound);
			else
				break; 
		} while(true); 
		return result; 
	} 
    public static int inputIntegerBetween(String message,
										  int lowBound,
										  int highBound) {
		int result = 0; 
	
		do {
			result = inputInteger(message);
			if (result < lowBound) 
				System.out.println("Please enter a value larger than or equal to "+lowBound);
			else if (result > highBound) 
				System.out.println("Please enter a value less than or equal to "+highBound);
			else
				break;
		} while (true); 
		return result; 
	} 
	
    public static double inputDouble(String message) {
	    Scanner userInput = new Scanner(System.in);
	    String result=""; 
	     
	    do {
	    	System.out.print(message);
		    result = userInput.nextLine(); 
		
		    if (isDouble(result))
			    break; 
		    else
			    System.out.println("Please enter a valid number! ");
	    } while (true); 
	    return Double.parseDouble(result); 
    }    
	public static int inputInteger(String message) {
	    Scanner userInput = new Scanner(System.in);
	    String result; 
	
	    System.out.print(message); 
	    do {
	    	result = userInput.nextLine(); 
	
	    	if (isInteger(result)) 
	    		break;
	    	
	    	else
	    		System.out.print("Please enter a valid integer: ");
	    } while(true); 
	    return Integer.parseInt(result); 
	}

	public static boolean isInteger(String string) {
	    try {
		    Integer.parseInt(string);
		    return true;
	    }
	
	    catch (NumberFormatException e) {
	    	return false; 
	    }
	}
	public static boolean isDouble(String string) {
		try {
			Double.parseDouble(string);
			return true; 
		}
		
		catch (NumberFormatException e) {
			return false; 
		}
	}
	
	public static double roundDouble(double value,int decimalPlaces) {			
		if (decimalPlaces <0)
			throw new IllegalArgumentException(); 
		return Math.round(value* Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces);
	}

    public static int random(int low, int high) {
    
    Random rand = new Random();
    return rand.nextInt(high-low+1)+low;
    
  }

    public static double random(double low, double high) {
  
    Random rand = new Random();
    return low + (high-low) * rand.nextDouble();
  }
  
}