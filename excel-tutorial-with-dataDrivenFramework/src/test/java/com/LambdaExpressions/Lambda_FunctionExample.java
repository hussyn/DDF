package com.LambdaExpressions;

import java.util.function.Function;

public class Lambda_FunctionExample {
	
		 
	    public static void main(String[] args) {
	       // int x = 0;//-Lambda expression's parameter x cannot redeclare another local variable defined in an enclosing scope. 
			// Create a Function from a lambda expression.
	        // It returns the argument multiplied by itself.
	        Function<Integer, Integer> func = x -> x * x;
	 
	        //Apply the function to an argument of given number.
	        //We call apply() on the Function object. This executes and returns the result.
	        int result = func.apply(20);
	        System.out.println(result);
	    }
}
