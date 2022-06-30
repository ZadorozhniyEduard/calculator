import java.util.Scanner;

public class Main {
	
    private static final Scanner scanner = new Scanner(System.in);
    private static final Calculator calculator = new Calculator();

    public static void main(String[] args) {
    	String totalLine = scanner.nextLine();
        
    	try {
    		String result = calculator.calculate(totalLine);
    		System.out.print(prepareOutput(result));
    	} catch (Exception exception) {
    		System.out.print(exception.getMessage());
    	}
    }
    
    private static String prepareOutput(String string) {
    	int length = string.length();
    	if (length <= 40) {
    		return string;
    	}
    	
    	return string.substring(0, 40) + "...";
    }
}