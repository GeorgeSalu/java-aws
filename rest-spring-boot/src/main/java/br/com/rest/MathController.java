package br.com.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {
	
	@RequestMapping( value =  "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double sum(@PathVariable(value = "numberOne") String numberOne,
					  @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		
		if(!isNumerico(numberOne) || !isNumerico(numberTwo)) {
			throw new Exception();
		}
		
		return convertToDouble(numberOne) + convertToDouble(numberTwo);
	}

	private Double convertToDouble(String strNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean isNumerico(String strNumber) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
