package com.gabriel.springalgawjava.domain.exceptions;

public class ResourceNotFoundException extends ResourceException {
    private static final long serialVersionUID = 1L;
    
	public ResourceNotFoundException(String message) {
		super(message);
	}
}