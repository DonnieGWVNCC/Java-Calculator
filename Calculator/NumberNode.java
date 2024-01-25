// Donnie Garrison
// CIT 272 Object Oriented Programming
// Module 13 - Final Project
// 05/12/2023

// DG COMPLETED
public class NumberNode implements ENode{
    
    protected double value;
    
    public NumberNode(double v){
        value = v;
    }
    
    @Override
    public double getValue(){
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(double value) {
        this.value = value;
    }
}
