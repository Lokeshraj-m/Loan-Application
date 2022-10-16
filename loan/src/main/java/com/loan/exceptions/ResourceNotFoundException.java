package com.loan.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	
	
	 
    public ResourceNotFoundException() {}
 
    public ResourceNotFoundException(String msg)
    {
        super(msg);
    }

}
