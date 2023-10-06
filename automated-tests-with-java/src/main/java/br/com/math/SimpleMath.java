package br.com.math;

public class SimpleMath {

	public Double sum(Double firstName,Double secondNumber) {
		return firstName + secondNumber;
	}
	
	public Double subtraction(Double firstName,Double secondNumber) {
		return firstName - secondNumber;
	}
	
	public Double multiplication(Double firstName,Double secondNumber) {
		return firstName * secondNumber;
	}
	
	public Double division(Double firstName,Double secondNumber) {
		return firstName / secondNumber;
	}
	
	public Double mean(Double firstName,Double secondNumber) {
		return (firstName + secondNumber) / 2;
	}
	
	public Double squareRoot(Double number) {
		return (Double) Math.sqrt(number);
	}
	
}
