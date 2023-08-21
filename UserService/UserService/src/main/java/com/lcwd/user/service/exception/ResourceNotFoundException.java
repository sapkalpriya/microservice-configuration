package com.lcwd.user.service.exception;

public class ResourceNotFoundException extends RuntimeException
{

	public ResourceNotFoundException() {
		super("Resource Not found on server");
		
	}
	
	
	public ResourceNotFoundException(String msg)
	{
		super(msg);
	}
	

}
