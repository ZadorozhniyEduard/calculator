public class Calculator {
	
	private static final String[] availableOperations = {"-", "+", "*", "/"};
	
    public String calculate(String totalLine) throws Exception {
        String result = null;
        int number = -1;
        
        String operation = findOperation(totalLine);
        
        if (operation == null) {
        	throw new Exception("Operation not found.");
        }

        String[] subStr = totalLine.split("\\" + operation);
        
        if (subStr.length != 2) {
        	throw new Exception("Invalid Operator. Please use only 1 operation");
        }
        
        String firstOperand = subStr[0].trim();
        String secondOperand = subStr[1].trim();

        if (!firstOperand.contains("\"")) {
        	throw new Exception("Invalid first operand.");
        }
        
        firstOperand = removeQuotes(firstOperand);
        
        if (!validateString(firstOperand)) {
        	throw new Exception("Incorrect value for first operand.");
        }
        
        if (secondOperand.contains("\"")) {
            if (operation == "*" || operation == "/") {
            	throw new Exception("You can't * or / two strings.");
            }
            
            secondOperand = removeQuotes(secondOperand);
            
            if (!validateString(secondOperand)) {
            	throw new Exception("Incorrect value for second operand.");
            }
        } else {
            if (operation == "+" || operation == "-") {
            	throw new Exception("You can't plus or minus string and number.");
            }
            try {
                number = Integer.parseInt(secondOperand);
            } catch (Exception e) {
            	throw new Exception("Can't parse second operand.");
            }
            if (!validateNumber(number)) {
            	throw new Exception("Incorrect value for second operand.");
            }
        }
        
        switch (operation) {
            case "+":
                result = firstOperand + secondOperand;
                break;
            case "-":
                result = firstOperand.replace(secondOperand, "");
                break; 
            case "*":
                result = firstOperand.repeat(number);
                break;
            case "/":
                int length = firstOperand.length();
                int remains = length / number;
                result = firstOperand.substring(0, remains);
                break;    
        }
        
        return result;       
    }
    
    private String findOperation(String totalLine) {
    	String operation = null;
    	
    	for (int i = 0; i < availableOperations.length; i++) {
            if (totalLine.contains(availableOperations[i])) {
                operation = availableOperations[i];
                break;
            }
        }
    	
    	return operation;
    }
    
    private boolean validateNumber(int number) {
    	if (number < 0 || number > 10) {
        	return false;
        }
    	
    	return true;
    }
    
    private boolean validateString(String string) {
    	if (string.length() > 10) {
        	return false;
        }
    	
    	return true;
    }
    
    private String removeQuotes(String string) {
    	return string.replace("\"", "");
    }
}