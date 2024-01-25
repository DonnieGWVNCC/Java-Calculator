// Donnie Garrison
// CIT 272 Object Oriented Programming
// Module 13 - Final Project
// 05/12/2023

import java.lang.Math;

public class OperatorNode implements ENode{
    
    private String operator;
    private ENode left;
    private ENode right;
    
    // write two constructors.  one that takes no parameters
    //      one that takes a String

    // DG COMPLETED
   public OperatorNode(){
        operator = null;
        left = null;
        right = null;
    }

   // DG COMPLETED
   public OperatorNode(String operator){
        this.operator = operator;
        left = null;
        right = null;
    }
    
    //write getters and setters

    // DG COMPLETED 
    public String getOperator() {
        return operator;
    }

    // DG COMPLETED
    public void setOperator(String operator) {
        this.operator = operator;
    }
    
    // DG COMPLETED
    public ENode getLeft() {
        return left;
    }

    // DG COMPLETED
    public void setLeft(ENode left) {
        this.left = left;
    }

    // DG COMPLETED
    public ENode getRight() {
        return right;
    }

    // DG COMPLETED
    public void setRight(ENode right) {
        this.right = right;
    }

    // WRITE THIS FUNCTION
    // DG COMPLETED
    public double getValue(){
        // get value of the left ENode
        // get value of the right ENode
        // perform the correct operation
        //      depending on the operator
        //      it will be one of 5 possible operators (Strings)
        //      using the left and right values

        // DG I have created two variables to contain the value of left and right
        // I have also created a default invalid variable to be returned
        double leftData = left.getValue();
        double rightData = right.getValue();
        double invalid = 0;

        // DG the math operations will be performed based on what operator is used
        if (operator.equals("^")){

            // DG must use "pow" instead of "^"
            return(Math.pow(leftData, rightData));
        } else if (operator.equals("*")){
            return(leftData * rightData);
        } else if (operator.equals("/")){
            return(leftData / rightData);
        } else if (operator.equals("+")){
            return(leftData + rightData);
        } else if (operator.equals("-")){
            return(leftData - rightData);
        }
    return invalid;
    } 
}
