// Donnie Garrison
// CIT 272 Object Oriented Programming
// Module 13 - Final Project
// 05/12/2023

import java.util.Scanner;
import java.util.Stack;

public class Calculator {

    // DG COMPLETED
    public static void main(String[] args) {
        
        // prompt the user to enter a mathematical expression until they enter 'quit'
        // check to see if the expression is valid
        // if it is
        //      build the expression tree
        //      perform the calculation
        //      show the output
        // if it is NOT valid
        //      print an error
        
        /// DG This step is creating a Scanner, in which this case I have named it 'scan'
        Scanner scan = new Scanner(System.in);

        // DG This step primes a String with the name, 'mathEquation'
        String mathEquation = "";
 
        /// DG This step creates a 'while' loop prompting the user for input of a mathematical equation
        /// If the user inputs the word "Quit", then the program stops
        while (! mathEquation.equals("Quit")){
            System.out.print("Enter the mathematical expression: ");
            mathEquation = scan.nextLine();

            /// DG This step creates a 'String[]' variable that I have named, 'tokens'
            /// This function splits the variable 'mathEquation'
            /// I have also called the 'isValidExpression()' function in main, to check if the input from the user is a valid or not
            /// The program prints out the length of the equation, the number of characters in the array
            /// It also prints out the answer of the equation entered by the user
            String[] tokens = mathEquation.split(" "); 
            if (isValidExpression(tokens)){
                ENode eData = buildExpressionTree(tokens);
                Double value = eData.getValue();
                System.out.println(value);
            }
            System.out.println("The number of tokens are: " + tokens.length); 
           
            
        }

    }

    // DG COMLPETED
    public static boolean isNumber(String token){
        // write the code so that this actually returns true or false
        // if the token is a valid number

        // DG if there is no token, it will return false
        if (token == null){
            return(false);
        }

        // DG if the token is read as a Double, it will return true
        try {
            Double.parseDouble(token);
            return(true);

        // DG this performs a catch statement and returns false
        }catch (NumberFormatException numberException){
            return(false);
        }    
    }

    // DG COMPLETED
    public static boolean isOperator(String token){
        // write the code so that this actually returns true or false
        // if the token is a valid operator

        // DG this checks to see if the token is a valid math operator
        // If so it will return true, else it returns false
        if (token.equals("+")){
            return(true);
        } else if (token.equals("-")){
            return(true);
        } else if (token.equals("*")){
            return(true);
        } else if (token.equals("/")){
            return(true);
        } else if (token.equals("^")){
            return(true);
        } 
        return(false);
    }

    // DG COMPLETED
    public static boolean isValidExpression(String[] tokens){
        if (tokens.length % 2 == 0){
            System.out.println("INVALID EXPRESSION");
            return(false);
        } else {
            for (int num=0; num<tokens.length; num++){
                if (num % 2 == 0){
                    if (! isNumber(tokens[num])){
                        System.out.println("INVALID NUMBER INPUT");
                        return(false);
                    }
                } else if (num % 2 != 0) {
                    if (! isOperator(tokens[num])){
                        System.out.println("INVALID OPERATOR");
                        return(false);
                    }
                }
                
            }
        }
        System.out.println("VALID EXPRESSION");
        return(true);
    }
    

    // DG COMPLETED
    public static int priority(String operator){
        // return logical integer values for the priority of 
        // the 'operator' string.  You can assume that it is a valid operator

        // DG I use a switch case statement where the priority of the operators correspond with an integer
        switch (operator){
        case "^":
            return(3);
        case "*":
        case "/":
            return(2);
        case "+":
        case "-":
            return(1);
        }
        return(0);
    }

    // DG COMPLETED
    public static ENode buildExpressionTree(String[] tokens){
        
        // DG this step create an ENode operandStack and an operatorStack for Strings
        Stack<ENode> operandStack = new Stack<>();
        Stack<String> operatorStack = new Stack<>();
        
        // DG this is the loop that is executed
        for (int i=0; i<tokens.length; i++){

            // DG if the input is a valid number, it will create a new NumberNode and be pushed onto the operandStack
            // Convert tokens[i] to Double
            if (isNumber(tokens[i])){
                double dataI = Double.parseDouble(tokens[i]);
                NumberNode top = new NumberNode(dataI);
                operandStack.push(top);

            // DG this is to be done if the token is NOT a number
            // Therefore, it must be an operator
            } else {
                
                // DG this while loop is executed if the operatorStack is not empty
                // And while the token being examined is less than or equal to the token on top of the stack
                while (! operatorStack.isEmpty() && priority(operatorStack.peek()) >= priority(tokens[i])){

                    // DG the top operator is popped off the stack
                    // Then a new OperatorNode is created with the popped operator as its data
                    String popString = operatorStack.pop();
                    OperatorNode opNode = new OperatorNode(popString);

                    // DG the top two operands are popped off the operandStack
                    // They are then set to the left and the right of the new OperatorNode that was created
                    ENode first = operandStack.pop();
                    ENode second = operandStack.pop();
                    opNode.setRight(first);
                    opNode.setLeft(second);

                    // DG the new operatorNode is then pushed onto the operandStack
                    operandStack.push(opNode);

                }
                
                // DG once the token being examined is greater than the token on top of the stack, it will be pushed onto the operatorStack
                operatorStack.push(tokens[i]);

            } 
        }

        // DG this will be done while the operatorStack is NOT empty
        while (! operatorStack.isEmpty()){

            // DG I will pop the top operator
            String top = operatorStack.pop();

            // DG I have created a new OperatorNode
            OperatorNode node = new OperatorNode(top);

            // DG I pop the top two operands
            // I'm not sure if I need a variable inside of the 'pop' method, I think I do
            // ASSIGN THESE TO VARIABLES
            ENode opOne = operandStack.pop();
            ENode opTwo = operandStack.pop();

            // DG I set those two operands to the left and right of the node I have created
            // Once again I believe I need variables for the operands I popped to put inside the setLeft and setRight parameters, I'm stuck on that
            node.setRight(opOne);
            node.setLeft(opTwo);

            // DG I will then push this operatorNode onto the operandStack
            operandStack.push(node);
            

        } 

        // DG this step is supposed to pop the operand stack and return the node as the root element
        ENode opPop = operandStack.pop();
        return(opPop);
    }
}
