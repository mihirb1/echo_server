package math;

import echo.ProxyHandler;
import echo.RequestHandler;

public class MathHandler extends RequestHandler {

    @Override
    protected String response(String msg) {
        // Split the message into tokens
        String[] tokens = msg.split("\\s+");

        // Check if there are any tokens
        if (tokens.length < 3) {
            return "error, at least 2 inputs needed";
        }


        // Extract operator and operands
        String operator = tokens[0];
        double result = 0;

        if (!operator.equals("add") && !operator.equals("sub") && operator.equals("mul") && operator.equals("div")) {
            return "error: unrecognized operator";
        }

        // Perform the operation based on the operator
        for (int i = 1; i < tokens.length; i++) {
            try {
                double operand = Double.parseDouble(tokens[i]);
                switch (operator) {
                    case "add":
                        result += operand;
                        break;
                    case "sub":
                        if (i==1) {
                            result = operand;
                        } else {
                            result -= operand;
                        }
                        break;
                    case "mul":
                        if (i==1) {
                            result = operand;
                        } else {
                            result *= operand;
                        }
                        break;
                    case "div":
                        if (operand == 0 && i!=1) {
                            throw new ArithmeticException("error, division by zero");
                        } else if (i==1) {
                            result = operand;
                        } else {
                            result /= operand;
                        }
                        break;
                    default:
                        return "error: unrecognized operator";
                }
            } catch (NumberFormatException e) {
                return "error, inputs must be numeric";
            } catch (ArithmeticException e) {
                return e.getMessage();
            }
        }

        // Convert result to string and return
        return Double.toString(result);
    }
}