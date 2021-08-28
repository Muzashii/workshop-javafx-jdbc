package model.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ValidationException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	//quarda os erros de diferentes partes do formulario
	private Map<String,String> errors = new HashMap<>();
	
	//validacao do formulario
	public ValidationException(String msg) {
		super(msg);
	}
	
	//retorna os erros
	public  Map<String,String> getErrors(){
		return errors;
	}
	
	//adiciona os erros
	public void addError(String fieldName, String errorMessage) {
		errors.put(fieldName, errorMessage);
		
	}

}
